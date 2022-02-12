package  fr.orsys.kingsley.kanbanWS.controller;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class ErreurController implements ErrorController {

       @RequestMapping("/erreur")
       public ModelAndView handleError(HttpServletRequest request) {

               Object codeRetour = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

               System.out.println(new Date() + " une erreur est survenue ! ");
               if (codeRetour != null) {
                       System.out.println("Code retour " + codeRetour);
               }
               return new ModelAndView("erreur");
       }
}
