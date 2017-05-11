package ring.system.controller.base;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 错误页面集成
 *  
 * @author ring
 * @date 2017年5月7日 下午2:55:30
 * @version V1.0
 */
@Controller
public class MainsiteErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError() {
		return "/error/404";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
