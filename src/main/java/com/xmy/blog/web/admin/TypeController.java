package com.xmy.blog.web.admin;import com.github.pagehelper.PageHelper;import com.github.pagehelper.PageInfo;import com.xmy.blog.entity.Type;import com.xmy.blog.service.TypeService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.validation.Valid;import java.util.List;/** * @author xmy * @date 2021/3/15 7:27 下午 */@Controller@RequestMapping("/admin")public class TypeController {    @Autowired    private TypeService typeService;    //分页查询分类=列表//    要引入分页插件，或许会有问题    @GetMapping("/types")    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNym")Integer pageNum) {        //按照排序字段，倒序 排序        String orderBy = "id desc";        PageHelper.startPage(pageNum,10,orderBy);        List<Type> list = typeService.getAllType();        PageInfo<Type> pageInfo = new PageInfo<Type>(list);        model.addAttribute("pageInfo",pageInfo);        return "admin/types";    }//    public String types(@PageableDefault(size=10,sort={"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model) {//        //将查询出来的数据放入前端的模型中//        model.addAttribute("page",typeService.listType(pageable));//        return "admin/types";//    }    @GetMapping("/types/input")    public String input(Model model) {        model.addAttribute("type", new Type());        return "admin/types-input";    }    @GetMapping("/types/{id}/input")    public String editInput(@PathVariable Long id, Model model) {        model.addAttribute("type",typeService.getType(id));        return "admin/types-input";    }    //BindingResult用来接收校验之后的结果    @PostMapping("/types")    public String post(@Valid Type type, BindingResult result,RedirectAttributes attributes) {        //检验是否已经有相同名称的type        Type type1 = typeService.getTypeByName(type.getName());        if (type1 != null) {            result.rejectValue("name","nameError","不能添加重复的分类");        }        //检验type为空        if (result.hasErrors()) {            return "admin/types-input";        }        //处理新增type时，type的id为null的情况        if (type.getId() == null && type.getName() != null) {            List<Type> typeList = typeService.getAllType();            int suitId = typeList.size() + 1;            type.setId((long) suitId);        }        int t = typeService.saveType(type);        if (t == 0) {            attributes.addFlashAttribute("message", "新增失败");        } else {            attributes.addFlashAttribute("message","新增成功");        }        return "redirect:/admin/types";    }    //根据id来修改数据库属性    @PostMapping("types/{id}")    public String editPost(@Valid Type type,BindingResult result,@PathVariable Long id,RedirectAttributes attributes) {        //检查是否有相同名称的type        Type type1 = typeService.getTypeByName(type.getName());        if (type1 != null) {            result.rejectValue("name","nameError","不能添加重复的分类");        }        //检查type是否为空        if (result.hasErrors()) {            return "admin/types-input";        }        int  t= typeService.updateType(type);        if (t == 0) {            attributes.addFlashAttribute("message","更新失败");        } else {            attributes.addFlashAttribute("message","更新成功");        }        return "redirect:/admin/types";    }    //根据id删除相应的标签分类    @GetMapping("types/{id}/delete")    public String delete(@PathVariable Long id, RedirectAttributes attributes) {        typeService.deleteType(id);        attributes.addFlashAttribute("message","删除成功");        return "redirect:/admin/types";    }}