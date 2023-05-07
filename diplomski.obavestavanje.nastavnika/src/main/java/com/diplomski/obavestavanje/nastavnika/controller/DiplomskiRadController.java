package com.diplomski.obavestavanje.nastavnika.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/diplomski_radovi")
public class DiplomskiRadController {

//    @Autowired
//    private DiplomskiRadService diplomskiRadService;
//
//    @GetMapping
//    List<Thesis> vratiDiplomskeRadove(){return diplomskiRadService.vratiDiplomskeRadove();}
//
//    @PostMapping("/napravi")
//    DiplomskiRad napraviDiplomski(@RequestBody DiplomskiRad diplomskiRad){return diplomskiRadService.sacuvajDiplomskiRad(diplomskiRad);}
//
//
//    @PutMapping("/{diplomskiRadId}/profesor/{profesorId}")
//    DiplomskiRad ubaciProfesoraNaDiplomskiRad(
//            @PathVariable Long diplomskiRadId,
//            @PathVariable Long profesorId
//    ){
//            ResponseEntity<DiplomskiRad> diplomskiRad=diplomskiRadService.vratiDiplomskiRadPoIdu(diplomskiRadId);
//            return null;
//    }
//    @GetMapping("/{pocetniPeriod}/{krajnjiPeriod}")
//    public List<DiplomskiRad> vratiDiplomskiRaduNekomPeriodu(
//            @RequestParam("pocetniPeriod")LocalDateTime pocetniPeriod,
//            @RequestParam("krajnjiPeriod")LocalDateTime krajnjiPeriod){
//        return diplomskiRadService.vratiDiplomskeRadovePoDatumu(pocetniPeriod,krajnjiPeriod);
//
//    }
//    @GetMapping("/pretraga{tema}")
//    public List<DiplomskiRad> vratiDiplomskiRaduNekomPeriodu(
//            @RequestParam("tema")String tema){
//        System.out.println(tema);
//        return diplomskiRadService.nadjiPoTemi(tema);
//
//    }
//    @GetMapping("/pretragaProfesor")
//    public List<DiplomskiRad> vratiDiplomskiRadPoEmailuProfesora(
//            @RequestBody Object email){
//        String pom=email.toString();
//        pom=pom.substring(16,pom.length()-1);
//        System.out.println(pom);
//        return diplomskiRadService.nadjiPoProfesoruEmail(pom);
//
//    }



}
