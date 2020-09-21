package com.gradel.service.user.infrastructure.db.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gradel.service.user.domain.aggregate.roleauth.IAuthService;
import com.gradel.service.user.infrastructure.db.dataobject.Auth;
import com.gradel.service.user.infrastructure.db.mapper.roleauth.AuthMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sdeven.chen.dongwei
 * @since 2020-09-06
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Auth> implements IAuthService {

}
