package com.xmy.blog.web.admin;import com.xmy.blog.po.Type;import com.xmy.blog.service.TypeService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Sort;import org.springframework.data.web.PageableDefault;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.jws.WebParam;import javax.validation.Valid;/** * @author xmy * @date 2021/3/15 7:27 下午 */@Controller@RequestMapping("/admin")public class TypeController {    @Autowired    private TypeService typeService;    @GetMapping("/types")    public String types(@PageableDefault(size=10,sort={"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model) {        //将查询出来的数据放入前端的模型中        model.addAttribute("page",typeService.listType(pageable));        return "admin/types";    }    @GetMapping("/types/input")    public String input(Model model) {        model.addAttribute("type", new Type());        return "admin/types-input";    }    //BindingResult用来接收校验之后的结果    @PostMapping("/types")    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes) {        //检验是否已经有相同名称的type        Type type1 = typeService.getTypeByName(type.getName());        if (type1 != null) {            result.rejectValue("name","nameError","不能添加重复的分类");        }        //检验type为空        if (result.hasErrors()) {            return "admin/types-input";        }        Type type2 = typeService.saveType(type);        if (type2 == null) {            attributes.addFlashAttribute("meaage", "操作失败");        } else {            attributes.addFlashAttribute("meaage","操作成功");        }        return "redirect:/admin/types";    }}