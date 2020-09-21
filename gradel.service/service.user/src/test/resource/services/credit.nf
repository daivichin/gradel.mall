{
    "indexs": [
        {
            "idx": "entCode",
            "name": "请输入企业统一信用代码"
        },
        {
            "idx": "tclScore",
            "mode": 2
        },
        {
            "idx": "rejectMsg",
            "mode": 2
        },
        {
            "idx": "generalCreditLimit",
            "mode": 2
        },
        {
            "idx": "hanlinhuiCreditLimit",
            "mode": 2
        },
        {
            "idx": "tclScoreCreditLimit",
            "mode": 2
        },
        {
            "idx": "creditLevel",
            "mode": 2
        }
    ],
    "nodes": [
        {
            "id": "initDef",
            "name": "初始变量",
            "component": "ruleset",
            "ruleset": ["queryTDReportCount=0;tdPreloanReportId=null;"],
            "next": "getEntInfo"
        },
        {
            "id": "getEntInfo",
            "name": "获取企业信息",
            "component": "enterprise_info",
            "next": [
				{
                    "condition": "whetherMultipleInvestmentBehaviors==4 || creditReportQuery6monthCount>5 || personalOverdueLatest2yearCount>=6",
                    "next": "disableScore"
				},
                {
                    "condition": "!empty(legalPersonName)&&!empty(legalPersonMobile)&&!empty(legalPersonId)",
                    "next": "submitTDReport"
                },
                {
                    "next": "personIndexScore"
                }
            ]
        },
        {
            "id": "submitTDReport",
            "name": "提交同盾个人贷前报告",
            "component": "submit_td_preloan_report",
            "next": [
                {
                    "condition": "tdPreloanReportId.success",
                    "next": "personIndexScore",
					               "error": "提交同盾个人贷前报告失败"
                }
            ]
        },
        {
            "id": "personIndexScore",
            "name": "个人基础指标分数",
            "component": "ruleset",
            "ruleset": [
                "personTotalScore=(gender==1?70:100)*0.01",
                "personTotalScore+=(age>60||age<22?0:(age>=50?80:(age>=30?100:60)))*0.01",
                "personTotalScore+=(maritalStatus==1?100:(maritalStatus==2?80:(maritalStatus==3?60:40)))*0.02",
                "personTotalScore+=(degreeEducation==1?100:(degreeEducation==2?80:(degreeEducation==3?50:30)))*0.01",
                "personTotalScore+=(familyaDebtRatio<=30?100:(familyaDebtRatio<=50?80:(familyaDebtRatio<=70?50:0)))*0.03",
                "personTotalScore+=(livingSituation==1?100:(livingSituation==2?80:(livingSituation==3?50:0)))*0.01",
                "personTotalScore+=(workingYears>=8?100:(workingYears>=5?80:(workingYears>=3?60:(workingYears>=1?40:20))))*0.02",
                "personTotalScore+=(whetherMultipleInvestmentBehaviors==1?100:(whetherMultipleInvestmentBehaviors==2?70:(whetherMultipleInvestmentBehaviors==3?40:0)))*0.02",
                "personTotalScore+=(houseAssetsStatus==1?100:(houseAssetsStatus==2?80:(houseAssetsStatus==3?50:(houseAssetsStatus==4?30:0))))*0.04",
                "personTotalScore+=(houseLoanSettledCount>=2?100:(houseLoanSettledCount==1?50:0))*0.02",
                "personTotalScore+=(carHoldingStatus==1?100:0)*0.02",
                "personTotalScore+=(familyAsset>=800?100:(familyAsset>=500?80:(familyAsset>=100?50:0)))*0.03",
                "personTotalScore+=(busiLoanCount==0?100:(busiLoanCount==2?80:(busiLoanCount==3?50:0)))*0.02",
                "personTotalScore+=(creditCard6monthUsage<15?100:(creditCard6monthUsage<30?80:(creditCard6monthUsage<45?60:(creditCard6monthUsage<60?40:(creditCard6monthUsage<80?20:0)))))*0.02",
                "personTotalScore+=(debitBalance>=200?100:(debitBalance>=100?80:(debitBalance>=50?50:20)))*0.03",
                "personTotalScore+=(creditReportQuery6monthCount<2?100:(creditReportQuery6monthCount<=5?40:0))*0.02",
                "personTotalScore+=(personalOverdueLatest2yearCount>=6?0:(100-personalOverdueLatest2yearCount*20))*0.03",
                "personTotalScore+=(legalActionCount==1?100:(legalActionCount==2?60:(legalActionCount==3?40:0)))*0.01"
            ],
            "next": [
                {
                    "condition": "tdPreloanReportId!=null&&tdPreloanReportId.success",
                    "next": "queryTDReport"
                },
                {
                    "next": "tongdunCal"
                }
            ]
        },
        {
            "id": "waitingCmp",
            "name": "延迟组件",
            "component": "waiting_cmp",
            "next": "queryTDReport"
        },
        {
            "id": "queryTDReport",
            "name": "查询同盾个人贷前报告",
            "component": "query_td_preloan_report",
            "next": [
                {
                    "condition": "tdPreloanReport.success",
                    "next": "parseTDReport"
                },
                {
                    "condition": "++queryTDReportCount<2",
                    "next": "waitingCmp",
                    "error":"查询同盾个人贷前报告超时"
                }
            ]
        },
        {
            "id": "parseTDReport",
            "name": "解析同盾个人贷前报告",
            "component": "parse_td_preloan_report",
            "next": "tongdunCal"
        },
        {
            "id": "tongdunCal",
            "name": "同盾指标计算",
            "component": "ruleset",
            "ruleset": [
                "personTotalScore+=(tongdun3monthMultiLoanCount==0?100:(tongdun3monthMultiLoanCount<=3?70:(tongdun3monthMultiLoanCount<=4?40:0)))*0.03"
            ],
            "next": "thirdCreditScore"
        },
        {
            "id": "thirdCreditScore",
            "name": "第三方征信分数",
            "component": "ruleset",
            "ruleset": [
                "thirdCreditScore=(haoxinduScore>=750?100:(haoxinduScore>=650?80:(haoxinduScore>=600?60:(haoxinduScore>=550?40:0))))*0.02",
                "thirdCreditScore+=(tongdunScore==0?100:(tongdunScore<=10?80:(tongdunScore<=30?40:0)))*0.06",
                "thirdCreditScore+=(zmScore>=900?100:(zmScore>=800?80:(zmScore>=700?60:(zmScore>=600?40:0))))*0.02"
            ],
            "next": [
                {
                    "condition": "haoxinduScore<550 || zmScore<600",
                    "next": "disableScore"
                },
                {
                    "next": "entBaseInfoScore"
                }
            ]
        },
        {
            "id": "entBaseInfoScore",
            "name": "企业基本情况分数",
            "component": "ruleset",
            "ruleset": [
                "entBaseInfoScore=(busiType==1?100:(busiType==2?80:(busiType==3?40:0)))*0.01",
                "entBaseInfoScore+=(busiYear>10?100:(busiYear>=6?80:(busiYear>=4?60:(busiYear>=1?40:0))))*0.02",
                "entBaseInfoScore+=(registerCapital>=100?100:(registerCapital>=50?50:0))*0.02",
                "entBaseInfoScore+=(busiPlaceType==1?100:50)*0.02",
                "entBaseInfoScore+=(busiPlaceSize>=200?100:(busiPlaceSize>=100?80:50))*0.02",
                "entBaseInfoScore+=(staffCount>=50?100:(staffCount>=20?80:(staffCount>=10?50:20)))*0.02",
                "entBaseInfoScore+=(brandBusiness==1?100:(brandBusiness==2?80:(brandBusiness==3?60:40)))*0.03",
                "entBaseInfoScore+=(businessVolumeGrowthYear==1?100:(businessVolumeGrowthYear==2?80:(businessVolumeGrowthYear==3?60:(businessVolumeGrowthYear==4?40:(businessVolumeGrowthYear==5?20:0)))))*0.04",
                "entBaseInfoScore+=(goodsSoldLimitSum>=1000?100:(goodsSoldLimitSum>=800?80:(goodsSoldLimitSum>=500?60:(goodsSoldLimitSum>=300?30:0))))*0.03",
                "entBaseInfoScore+=(applicationDailyInventoryStatus>=2000?100:(applicationDailyInventoryStatus>=1000?80:(applicationDailyInventoryStatus>=800?60:(applicationDailyInventoryStatus>=500?40:(applicationDailyInventoryStatus>=300?20:0)))))*0.03",
                "entBaseInfoScore+=(latestBankIncomeJournal>=2000?100:(latestBankIncomeJournal>=1000?80:(latestBankIncomeJournal>=800?60:(latestBankIncomeJournal>=500?40:(latestBankIncomeJournal>=300?20:0)))))*0.07",
                "entBaseInfoScore+=(overdueLatest2yearCount>3?0:(overdueLatest2yearCount==2?50:(overdueLatest2yearCount==1?80:100)))*0.05",
                "entBaseInfoScore+=(matureLatest1yearDebit>=500?100:(matureLatest1yearDebit>=300?80:(matureLatest1yearDebit>=100?50:20)))*0.04",
                "entBaseInfoScore+=(loanOrgCount>2?100:(loanOrgCount>=1?50:0))*0.02",
                "entBaseInfoScore+=(partnerYears>8?100:(partnerYears>5?80:(partnerYears>3?60:30)))*0.03",
                "entBaseInfoScore+=(partnerContinuity==1?100:(partnerContinuity==2?80:(partnerContinuity==3?60:(partnerContinuity==4?40:0))))*0.04",
                "entBaseInfoScore+=(highlyYearPurchaseAmount>=1300?100:(highlyYearPurchaseAmount>=1100?80:(highlyYearPurchaseAmount>=700?60:(highlyYearPurchaseAmount>=500?40:(highlyYearPurchaseAmount>=300?20:0)))))*0.04",
                "entBaseInfoScore+=(paymentDaysOverdue==1?100:(paymentDaysOverdue==2?80:(paymentDaysOverdue==3?60:(paymentDaysOverdue==4?40:0))))*0.04",
                "entBaseInfoScore+=(highlyCustomerEvaluation==1?100:(highlyCustomerEvaluation==2?80:(highlyCustomerEvaluation==3?60:(highlyCustomerEvaluation==4?30:0))))*0.02"
            ],
            "next": [
                {
                    "condition": "busiYear<1 || businessVolumeGrowthYear==6",
                    "next": "disableScore"
                },
                {
                    "condition": "paymentDaysOverdue==5 || overdueLatest2yearCount>=3",
                    "next": "saveBlackList"
                },
                {
                    "next": "subTotalScore"
                }
            ]
        },
        {
            "id": "subTotalScore",
            "name": "最终客户得分",
            "component": "ruleset",
            "ruleset": [
                "tclScore=personTotalScore+thirdCreditScore+entBaseInfoScore"
            ],
            "next": "caculatePre"
        },
        {
            "id": "caculatePre",
            "name": "额度计算前置",
            "component": "ruleset",
            "ruleset": [
                "coefficient1=(tclScore>=90?1.2:(tclScore>=80?1.1:(tclScore>=75?1:(tclScore>=65?0.8:(tclScore>=60?0:0)))))",
                "coefficient2=(busiYear>=7?1.1:(busiYear>=5?1:(busiYear>=3?0.9:(busiYear>=1?0.6:0))))",
                "coefficient3=(familyAsset>1200?1.2:(familyAsset>=1000?1.1:(familyAsset>=800?1:(familyAsset>=600?0.9:(familyAsset>=500?0.8:0.6)))))"
            ],
            "next": "caculateLimit"
        },
        {
            "id": "caculateLimit",
            "name": "计算三种额度",
            "component": "ruleset",
            "ruleset": [
                "generalCreditLimit=(latestBankIncomeJournal/12)*2*coefficient1*coefficient2*coefficient3",
                "hanlinhuiCreditLimit=(highlyYearPurchaseAmount/12)*2*coefficient1*coefficient2*coefficient3",
                "tclScoreCreditLimit=(tclScore>=96?500:(tclScore>=94?400:(tclScore>=92?350:(tclScore>=90?300:(tclScore>=87?250:(tclScore>=84?200:(tclScore>=80?180:(tclScore>=77?150:(tclScore>=74?100:(tclScore>=70?80:(tclScore>=68?60:(tclScore>=66?50:(tclScore>=63?40:(tclScore>=60?20:(tclScore>=55?0:0)))))))))))))))"
            ],
            "next": "creditLevel"
        },
        {
            "id": "saveBlackList",
            "name": "用户放入黑名单",
            "component": "save_black_list",
            "next": "disableScore"
        },
        {
            "id": "disableScore",
            "name": "总分置0",
            "component": "ruleset",
            "ruleset": [
                "tclScore=0",
				"rejectMsg=whetherMultipleInvestmentBehaviors==4?'开发房地产、投资矿产、股票、娱乐场所、民间借贷等其他高风险投资不准入;':''",
				"rejectMsg+=creditReportQuery6monthCount>5?'近六个月内查询次数（仅统计贷款审批）超过5次以上不准入;':''",
				"rejectMsg+=personalOverdueLatest2yearCount>=6?'近2年内贷款及信用卡逾期次数超过6次以上不准入;':''",
				"rejectMsg+=haoxinduScore<550?'550分以下不准入;':''",
				"rejectMsg+=zmScore<600?'600分以下不准入;':''",
				"rejectMsg+=busiYear<1?'企业经营时间1年以下不准入;':''",
				"rejectMsg+=businessVolumeGrowthYear==6?'业务规模同比增长情况（与上一整年对比）下降超过50%不准入;':''",
				"rejectMsg+=paymentDaysOverdue==5?'账期逾期情况（按翰林汇评价标准）近2年内累计超过6次不准入;':''",
				"rejectMsg+=overdueLatest2yearCount>=3?'近两年内贷款逾期次数3次及以上不准入;':''"
            ],
            "next": "creditLevel"
        },
        {
            "id": "creditLevel",
            "name": "客户评级",
            "component": "ruleset",
            "ruleset": [
                "creditLevel=(tclScore>=90?'AAA':(tclScore>=80?'AA':(tclScore>=75?'A':(tclScore>=65?'BBB':(tclScore>=60?'BB':(tclScore>=55?'B':'C'))))))"
            ],
            "next": "updateFinalResult"
        },
        {
            "id": "updateFinalResult",
            "name": "保存计算结果",
            "component": "enterprise_info_save"
        }
    ]
}