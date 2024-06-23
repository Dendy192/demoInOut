package com.dendy.countinout.controller;

import com.dendy.countinout.dao.model.primary.SYSACCESModel;
import com.dendy.countinout.dao.service.primary.SYSACCESService;
import com.dendy.countinout.form.LoginForm;
import com.dendy.countinout.utils.LabelUtils;
import com.dendy.countinout.vo.MessageVo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    SYSACCESService sysaccesService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(@ModelAttribute("loginForm") LoginForm form, HttpServletRequest request) {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSystem(@ModelAttribute("loginForm") LoginForm form, HttpServletRequest request) {
        List<SYSACCESModel> sysaccesModel = sysaccesService.findAll();
        for (SYSACCESModel model : sysaccesModel) {
            if (form.getUsername().equalsIgnoreCase(model.getUsername())) {
                request.getSession().setAttribute("usernameLogin", form.getUsername());
                return "redirect:/dashboard";
            }
        }
        MessageVo message = new MessageVo();
        message.setContent(LabelUtils.loginContentError);
        message.setType(LabelUtils.typeError);
        message.setCssClass(LabelUtils.cssError);
        request.getSession().setAttribute("msgLogin", message);
        return "redirect:/";
    }

}
