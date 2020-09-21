package com.gradel.service.user.infrastructure.db.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gradel.service.user.domain.aggregate.roleauth.IRoleService;
import com.gradel.service.user.infrastructure.db.dataobject.Role;
import com.gradel.service.user.infrastructure.db.mapper.roleauth.RoleMapper;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
