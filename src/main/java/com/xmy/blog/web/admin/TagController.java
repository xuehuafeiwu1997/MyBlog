package com.xmy.blog.web.admin;import com.xmy.blog.po.Tag;import com.xmy.blog.service.TagService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Sort;import org.springframework.data.web.PageableDefault;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.validation.BindingResult;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.validation.Valid;/** * @author xmy * @date 2021/3/17 11:47 上午 */@Controller@RequestMapping("/admin")public class TagController {    @Autowired    private TagService tagService;    @GetMapping("/tags")    public String tags(@PageableDefault(size = 10,sort={"id"},direction = Sort.Direction.DESC) Pageable pageable,Model model) {        //将查询出来的数据放入到前端的模型中        model.addAttribute("page",tagService.listTag(pageable));        return "admin/tags";    }    @GetMapping("/tags/input")    public String input(Model model) {        model.addAttribute("tag",new Tag());        return "admin/tags-input";    }    @PostMapping("/tags")    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {        //检验是否已经有相同名称的tag        Tag tag1 = tagService.getTagByName(tag.getName());        if (tag1 != null) {            result.rejectValue("name","nameError","不能添加重复的标签");        }        //检查tag是否为空        if (result.hasErrors()) {            return "admin/tags-input";        }        Tag tag2 = tagService.saveTag(tag);        if (tag2 == null) {            attributes.addFlashAttribute("message","新增失败");        } else {            attributes.addFlashAttribute("message","新增成功");        }        return "redirect:/admin/tags";    }    //根据id删除相应的标签    @GetMapping("tags/{id}/delete")    public String delete(@PathVariable Long id,RedirectAttributes attributes) {        tagService.deleteTag(id);        attributes.addFlashAttribute("message","删除成功");        return "redirect:/admin/tags";    }    //点击编辑进入标签的编辑界面    @GetMapping("tags/{id}/input")    public String editInput(@PathVariable Long id,Model model) {        model.addAttribute("tag",tagService.getTag(id));        return "admin/tags-input";    }    //点击编辑后的提交实现    @PostMapping("tags/{id}")    public String editPost(@Valid Tag tag,BindingResult result,@PathVariable Long id,RedirectAttributes attributes) {        //检查是否有相同名称的tag        Tag tag1 = tagService.getTagByName(tag.getName());        if (tag1 != null) {            result.rejectValue("name","nameError","不能添加重复的标签");        }        //检查tag是否为空        if (result.hasErrors()) {            return "admin/tags-input";        }        Tag tag2 = tagService.updateType(id,tag);        if (tag2 == null) {            attributes.addFlashAttribute("message","更新失败");        } else {            attributes.addFlashAttribute("message","更新成功");        }        return "redirect:/admin/tags";    }}