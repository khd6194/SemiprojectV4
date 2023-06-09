package honeybee.spring4mvc.semiprojectv4.controller;

import honeybee.spring4mvc.semiprojectv4.model.Board;
import honeybee.spring4mvc.semiprojectv4.model.Member;
import honeybee.spring4mvc.semiprojectv4.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService bsrv;

    @GetMapping("/list")
    public ModelAndView list(int cpg) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board/list.tiles");
        mv.addObject("bd",bsrv.showBoard(cpg));
        mv.addObject("cpg",cpg);
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", bsrv.countBoard());
        return mv;
    }
    @GetMapping("/find") // 검색하기
    public ModelAndView find(int cpg, String ftype, String fkey){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("board/list.tiles");
        mv.addObject("bd",bsrv.showBoard(cpg,ftype,fkey));
        mv.addObject("cpg",cpg);
        mv.addObject("stpg", ((cpg - 1) / 10) * 10 + 1);
        mv.addObject("cntpg", bsrv.countBoard(ftype,fkey));

        return mv;
    }
    @GetMapping("/write")
    public String write(){
        return "board/write.tiles";
    }
    @PostMapping("/write")
    public String writeok(Board b){
        String view = "error.tiles";

        if(bsrv.newBoard(b)){view = "redirect:/board/list?cpg=1";}

        return view;
    }
    @GetMapping("/view")
    public String view(){
        return "board/view.tiles";
    }



}
