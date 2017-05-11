package ring.common.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * 自定义密码比较器
 *  
 * @author ring
 * @date 2017年4月26日 下午4:56:51
 * @version V1.0
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher{

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken utoken = (UsernamePasswordToken) token;
		//获得用户输入的密码（可以采用加盐的方式去检验）
		String inPassword = new String(utoken.getPassword());
		////获得数据库密码
		String dbPassword = (String) info.getCredentials();
		return this.equals(inPassword,dbPassword);
	}
	
}
