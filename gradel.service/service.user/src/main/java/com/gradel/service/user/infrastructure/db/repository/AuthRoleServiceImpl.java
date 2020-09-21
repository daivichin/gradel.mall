package com.gradel.service.user.infrastructure.db.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gradel.service.user.domain.aggregate.roleauth.IAuthRoleService;
import com.gradel.service.user.infrastructure.db.dataobject.AuthRole;
import com.gradel.service.user.infrastructure.db.mapper.roleauth.AuthRoleMapper;
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
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements IAuthRoleService {

}
