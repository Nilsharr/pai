
package controllers;

import beans.Pracownik;
import dao.PracownikDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao;
    
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }
    
    @RequestMapping("/addForm")
    public String showform(Model m){
        m.addAttribute("command", new Pracownik());
        return "addForm";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr){
        dao.save(pr);
        return "redirect:/viewAll";
    }
    
    @RequestMapping("/viewAll")
    public String viewAll(Model m){
        List<Pracownik> list=dao.getAll();
        m.addAttribute("list",list);
        return "viewAll";
    }
    
    @RequestMapping(value="/edit/{id}")
    public String edit(@PathVariable int id, Model m){
        Pracownik prac = dao.getPracownikById(id);
        m.addAttribute("command",prac);
        return "editForm";
    }
    
    @RequestMapping(value="/editsave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("pr") Pracownik pr){
        dao.update(pr);
        return "redirect:/viewAll";
    }    
    
    @RequestMapping(value="/delete/{id}")
    public String delete(@PathVariable int id){
        dao.delete(id);
        return "redirect:/viewAll";
    }
}
