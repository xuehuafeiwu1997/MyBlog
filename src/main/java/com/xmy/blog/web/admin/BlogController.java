package com.xmy.blog.web.admin;import com.github.pagehelper.PageHelper;import com.github.pagehelper.PageInfo;import com.xmy.blog.entity.Blog;import com.xmy.blog.entity.User;import com.xmy.blog.service.BlogService;import com.xmy.blog.service.TagService;import com.xmy.blog.service.TypeService;import com.xmy.blog.vo.BlogQuery;import com.xmy.blog.vo.SearchBlog;import com.xmy.blog.vo.ShowBlog;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Controller;import org.springframework.ui.Model;import org.springframework.web.bind.annotation.*;import org.springframework.web.servlet.mvc.support.RedirectAttributes;import javax.servlet.http.HttpSession;import java.util.List;/** * @author xmy * @date 2021/3/15 11:18 上午 */@Controller@RequestMapping("/admin")public class BlogController {    @Autowired    private BlogService blogService;    @Autowired    private TypeService typeService;    @Autowired    private TagService tagService;    //博客列表    @GetMapping("/blogs")    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {        //按照排序字段 倒序 排序        String orderBy = "update_time desc";        PageHelper.startPage(pageNum,10,orderBy);        List<BlogQuery> list = blogService.getAllBlog();        PageInfo<BlogQuery> pageInfo = new PageInfo<BlogQuery>(list);        model.addAttribute("types",typeService.getAllType());        model.addAttribute("pageInfo",pageInfo);//        model.addAttribute("types",typeService.listType());//        model.addAttribute("page",blogService.listBlog(pageable, blogQuery));        return "/admin/blogs";    }    //搜索博客    @PostMapping("/blogs/search")    //点击下一页只进行表格部分的刷新    public String search(SearchBlog searchBlog, Model model,                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {        List<BlogQuery> blogBySearch = blogService.searchByTitleOrTypeOrRecommend(searchBlog);        PageHelper.startPage(pageNum, 10);        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);        model.addAttribute("pageInfo", pageInfo);        return "admin/blogs :: blogList";    }//    public String search(@PageableDefault(size = 2,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, BlogQuery blogQuery, Model model) {//        model.addAttribute("page",blogService.listBlog(pageable, blogQuery));//        return "/admin/blogs :: blogList";//    }    //跳转到博客新增界面    @GetMapping("/blogs/input")    public String input(Model model) {        setTypeAndTag(model);        model.addAttribute("blog",new Blog());        return "/admin/blogs-input";    }    @GetMapping("/blogs/{id}/input")    public String editInput(@PathVariable Long id, Model model) {        setTypeAndTag(model);//        ShowBlog blogById = blogService.getBlogById(id);//        model.addAttribute("blog",blogById);        Blog blog = blogService.getBlog(id);        blog.init();        model.addAttribute("blog",blog);        return "/admin/blogs-input";    }    private void setTypeAndTag(Model model) {        model.addAttribute("types",typeService.getAllType());        model.addAttribute("tags",tagService.getAllTag());    }    //博客新增    @PostMapping("/blogs")    //HttpSession中有user对象    public String post(Blog blog, RedirectAttributes attributes,HttpSession session) {        blog.setUser((User) session.getAttribute("user"));        blog.setType(typeService.getType(blog.getType().getId()));        blog.setTags(tagService.getAllTag());//这样写试一下//        blog.setTags(tagService.listTag(blog.getTagIds()));        blog.setUser(blog.getUser());//        int b;//        if (blog.getId() == null) {//            b = blogService.saveBlog(blog);//        } else {//            b = blogService.updateBlog();//        }        int b = blogService.saveBlog(blog);        if (b == 0) {            attributes.addFlashAttribute("message","操作失败");        } else {            attributes.addFlashAttribute("message","操作成功");        }        return "redirect:/admin/blogs";    }    @GetMapping("blogs/{id}/delete")    public String delete(@PathVariable Long id, RedirectAttributes attributes) {        blogService.deleteBlog(id);        attributes.addFlashAttribute("message","删除成功");        return "redirect:/admin/blogs";    }}