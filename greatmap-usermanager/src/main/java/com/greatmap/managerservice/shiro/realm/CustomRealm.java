package com.greatmap.managerservice.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.greatmap.managerservice.mapper.RolesPermissionsMapper;
import com.greatmap.managerservice.mapper.UserRolesMapper;
import com.greatmap.managerservice.mapper.UsersMapper;
import com.greatmap.managerservice.pojo.RolesPermissions;
import com.greatmap.managerservice.pojo.RolesPermissionsExample;
import com.greatmap.managerservice.pojo.UserRoles;
import com.greatmap.managerservice.pojo.UserRolesExample;
import com.greatmap.managerservice.pojo.Users;
import com.greatmap.managerservice.pojo.UsersExample;
import com.greatmap.managerservice.pojo.UsersExample.Criteria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private UsersMapper usermapper;
	@Autowired
	private UserRolesMapper userRolesMapper;
	@Autowired
	private RolesPermissionsMapper rolesPermissionsMapper;

	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String userName = (String) principals.getPrimaryPrincipal();

		// 从数据库或者缓存中获取角色数据
		Set<String> roles = getRolesByUserName(userName);

		Set<String> permissions = getPermissionsByUserName(userName);

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.setRoles(roles);
		simpleAuthorizationInfo.setStringPermissions(permissions);
		return simpleAuthorizationInfo;
	}

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// 1.从主体传过来的认证信息中，获得用户名
		String userName = (String) token.getPrincipal();

		// 2.通过用户名到数据库中获取凭证

		String password = getPasswordByUserName(userName);

		if (password == null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, "customRealm");

		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(userName));
		return authenticationInfo;
	}

	// 模拟数据库查询凭证
	private String getPasswordByUserName(String userName) {

		UsersExample example = new UsersExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<Users> list = usermapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			Users user = list.get(0);
			System.out.println("password" + user.getPassword());
			return user.getPassword();
		}
		return null;

	}
	// 模拟数据库或缓存中

	private Set<String> getRolesByUserName(String userName) {

		System.out.println("从数据库中获取授权数据");
		Set<String> sets = new HashSet<String>();

		UserRolesExample example = new UserRolesExample();
		com.greatmap.managerservice.pojo.UserRolesExample.Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(userName);
		List<UserRoles> list = userRolesMapper.selectByExample(example);
		if (list != null && list.size() > 0) {

			for (int i = 0; i < list.size(); i++) {
				UserRoles userRoles = list.get(i);
				System.out.println("rolename" + userRoles.getRoleName());
				sets.add(userRoles.getRoleName());
			}

		}

		return sets;

	}

	private Set<String> getPermissionsByUserName(String userName) {
		Set<String> sets = new HashSet<String>();
		Set<String> set = getRolesByUserName(userName);

		for (String str : set) {
			System.out.println(str);
			RolesPermissionsExample rolesPermissionsExample = new RolesPermissionsExample();
			com.greatmap.managerservice.pojo.RolesPermissionsExample.Criteria criteria = rolesPermissionsExample
					.createCriteria();
			criteria.andRoleNameEqualTo(str);
			List<RolesPermissions> list = rolesPermissionsMapper.selectByExample(rolesPermissionsExample);

			if (list != null && list.size() > 0) {

				for (int i = 0; i < list.size(); i++) {
					RolesPermissions rolesPermissions = list.get(i);
					System.out.println("rolesPermissions" + rolesPermissions.getRoleName());
					sets.add(rolesPermissions.getPermission());
				}

			}

		}

		return sets;
	}

	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123456", "Mark");

		System.out.println(md5Hash.toString());
	}
}
