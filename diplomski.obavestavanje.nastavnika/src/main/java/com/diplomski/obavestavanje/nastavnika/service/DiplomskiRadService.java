package com.diplomski.obavestavanje.nastavnika.service;

import org.springframework.stereotype.Service;

@Service
public class DiplomskiRadService {

//    @Autowired
//    private DiplomskiRadRepository diplomskiRadRepository;
//    @Autowired
//    private ProfesorRepository profesorRepository;
//
//
//    public DiplomskiRad sacuvajDiplomskiRad(DiplomskiRad diplomskiRad){
//        return diplomskiRadRepository.save(diplomskiRad);
//    }
//    public List<DiplomskiRad> vratiDiplomskeRadove(){
//        return diplomskiRadRepository.findAll();
//    }
//    public ResponseEntity<DiplomskiRad> vratiDiplomskiRadPoIdu(Long id){
//        DiplomskiRad diplomskiRad=diplomskiRadRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Ne postoji"));
//
//        return ResponseEntity.ok(diplomskiRad);
//
//    }
//
//
//    public List<DiplomskiRad> vratiDiplomskeRadovePoDatumu(LocalDateTime pocetniPeriod, LocalDateTime krajnjiPeriod) {
//        return diplomskiRadRepository.vratiDiplomsiRadUOdredjenomPeriodu(pocetniPeriod,krajnjiPeriod);
//    }
//
//    public List<DiplomskiRad> nadjiPoTemi(String tema) {
//        return diplomskiRadRepository.findByTemaDiplomskogRada(tema);
//    }
//
//    public List<DiplomskiRad> nadjiPoProfesoru(String email) {
//
//        Profesor profesor=profesorRepository.findByEmailProfesora(email);
//        System.out.println(profesor);
//        return diplomskiRadRepository.findByProfesori(profesor);
//    }
//    public List<DiplomskiRad> nadjiPoProfesoruEmail(String email) {
//      return diplomskiRadRepository.findByProfesoriEmailProfesora(email);
//    }
}
