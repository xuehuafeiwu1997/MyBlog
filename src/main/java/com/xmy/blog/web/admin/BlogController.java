package com.xmy.blog.web.admin;import com.sun.org.apache.xpath.internal.operations.Mod;import com.xmy.blog.po.Blog;import com.xmy.blog.po.User;import com.xmy.blog.service.BlogService;import com.xmy.blog.service.TagService;import com.xmy.blog.service.TypeService;import com.xmy.blog.vo.BlogQuary;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Pageable;import org.springframework.data.domain.Sort;import org.springframework.data.web.PageableDefault;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.servlet.http.HttpSession;/** * @author xmy * @date 2021/3/15 11:18 上午 */@Controller@RequestMapping("/admin")public class BlogController {    @Autowired    private BlogService blogService;    @Autowired    private TypeService typeService;    @Autowired    private TagService tagService;    @GetMapping("/blogs")    public String blogs(@PageableDefault(size = 2,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuary blogQuary, Model model) {        model.addAttribute("types",typeService.listType());        model.addAttribute("page",blogService.listBlog(pageable,blogQuary));        return "/admin/blogs";    }    @PostMapping("/blogs/search")    //点击下一页只进行表格部分的刷新    public String search(@PageableDefault(size = 2,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuary blogQuary, Model model) {        model.addAttribute("page",blogService.listBlog(pageable,blogQuary));        return "/admin/blogs :: blogList";    }    @GetMapping("/blogs/input")    public String input(Model model) {        setTypeAndTag(model);        model.addAttribute("blog",new Blog());        return "/admin/blogs-input";    }    @GetMapping("/blogs/{id}/input")    public String editInput(@PathVariable Long id, Model model) {        setTypeAndTag(model);        Blog blog = blogService.getBlog(id);        blog.init();        model.addAttribute("blog",blogService.getBlog(id));        return "/admin/blogs-input";    }    private void setTypeAndTag(Model model) {        model.addAttribute("types",typeService.listType());        model.addAttribute("tags",tagService.listTag());    }    @PostMapping("/blogs")    //HttpSession中有user对象    public String post(Blog blog, RedirectAttributes attributes,HttpSession session) {        blog.setUser((User) session.getAttribute("user"));        blog.setType(typeService.getType(blog.getType().getId()));        blog.setTags(tagService.listTag(blog.getTagIds()));        Blog b = blogService.save(blog);        if (b == null) {            attributes.addFlashAttribute("message","操作失败");        } else {            attributes.addFlashAttribute("message","操作成功");        }        return "redirect:/admin/blogs";    }    @GetMapping("blogs/{id}/delete")    public String delete(@PathVariable Long id, RedirectAttributes attributes) {        blogService.deleteBlog(id);        attributes.addFlashAttribute("message","删除成功");        return "redirect:/admin/blogs";    }}