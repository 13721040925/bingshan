package cn.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import cn.pojo.User;
import cn.service.UserService;

@Controller
@RequestMapping("/ice")
public class MainController {
	@Resource
	private UserService userService;

	@RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String login(String tel, String password) {
		User user = new User();
		user.setTel(tel);
		user.setPassword(password);
		User u = userService.login(user);
		if (u != null) {
			return JSON.toJSONString("ok");
		}
		return JSON.toJSONString("no");
	}

	@RequestMapping(value = "/checkUser", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String checkUser(String tel) {
		User u = userService.checkUser(tel);
		if (u == null) {
			return JSON.toJSONString("ok");
		}
		return JSON.toJSONString("no");
	}

	@RequestMapping(value = "/reg", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String reg(String tel, String password) {
		User user = new User();
		user.setTel(tel);
		user.setPassword(password);
		user.setName(tel);
		int i = userService.regist(user);
		if (i > 0) {
			return JSON.toJSONString("ok");
		}
		return JSON.toJSONString("no");
	}

	@RequestMapping(value = "/updatepass", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updatepass(String tel, String password) {
		User user = new User();
		user.setTel(tel);
		user.setPassword(password);
		int i = userService.update(user);
		if (i > 0) {
			return JSON.toJSONString("ok");
		}
		return JSON.toJSONString("no");
	}

	@RequestMapping(value = "/sendSms", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String sendSms(HttpServletRequest request, HttpServletResponse response, String tel) {
		try {
			JSONObject json = null;
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
			SmsSingleSender ssender = new SmsSingleSender(1400176976, "dce1cef81fe79d900167fe528a6c616a");
			SmsSingleSenderResult result = ssender.send(0, "86", tel,
					"【来自冰山的贪吃蛇】您的验证码是: " + verifyCode + "，该码有效期为5分钟，该码只能使用一次！如非本人操作，请忽略本短信。", "", "");
			json = JSONObject.parseObject(result.toString());//
			if (json.getIntValue("code") != 0) {// 发送短信失败
				return null;
			}
			HttpSession session = request.getSession();
			json = new JSONObject();
			json.put("verifyCode", verifyCode);
			json.put("createTime", System.currentTimeMillis());
			session.setAttribute("verifyCode", json);
			System.out.println(json);// 验证码，和发送的时间(createTime)
			return JSON.toJSONString(json);
		} catch (Exception e) {
			// HTTP响应码错误
			e.printStackTrace();
		}
		return null;
	}
}
