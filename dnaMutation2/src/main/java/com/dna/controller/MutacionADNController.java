package com.dna.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dna.entity.DnaVerification;
import com.dna.repository.DnaVerificationRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@ComponentScan
@Api(value = "MutacionADNController", description = "MUtation detention")
public class MutacionADNController {
	

    @Autowired
    private DnaVerificationRepository dnaVerificationRepository;

    @PostMapping("/mutation")
    @ApiOperation(value = "Obtener mutation", response = String.class)
    public ResponseEntity<String> detectarMutacion(@RequestBody Map<String, String[]> requestBody) {
        String[] secuenciaADN = requestBody.get("dna");
        boolean tieneMutacion = hasMutation(secuenciaADN);

        DnaVerification dnaVerification = new DnaVerification();
        dnaVerification.setDnaSequence(String.join(",", secuenciaADN));
        dnaVerification.setMutated(tieneMutacion);

        dnaVerificationRepository.save(dnaVerification);

        if (tieneMutacion) {
            return ResponseEntity.ok("Se encontró una mutación.");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No se encontró una mutación.");
        }
    }
    
    @GetMapping("/hello")
    @ApiOperation(value = "Obtener un saludo", response = String.class)
    public ResponseEntity<?> getHello() {
    
       return ResponseEntity.ok("Hello Mutation");
    }
	
	@GetMapping("/stats")
	@ApiOperation(value = "Obtener un listado de valores", response = String.class)
    public ResponseEntity<Map<String, Object>> getStats() {
    long countMutations = dnaVerificationRepository.countByMutated(true);
    long countNoMutation = dnaVerificationRepository.countByMutated(false);

    double ratio = countNoMutation == 0 ? 1.0 : (double) countMutations / countNoMutation;

    Map<String, Object> stats = new HashMap<>();
    stats.put("count_mutations", countMutations);
    stats.put("count_no_mutation", countNoMutation);
    stats.put("ratio", ratio);

    return ResponseEntity.ok(stats);
   }
   
   /***
     * Merodo para detectar la mutacion
     * @param dna
     * @return
     */
    private boolean hasMutation(String[] dna) {
    	 int n = dna.length;
         
         // Verificar filas y columnas
         for (int i = 0; i < n; i++) {
             for (int j = 0; j < n; j++) {
                 char base = dna[i].charAt(j);
                 
                 // Verificar horizontalmente
                 if (j + 3 < n &&
                     base == dna[i].charAt(j + 1) &&
                     base == dna[i].charAt(j + 2) &&
                     base == dna[i].charAt(j + 3)) {
                     return true;
                 }
                 
                 // Verificar verticalmente
                 if (i + 3 < n &&
                     base == dna[i + 1].charAt(j) &&
                     base == dna[i + 2].charAt(j) &&
                     base == dna[i + 3].charAt(j)) {
                     return true;
                 }
                 
                 // Verificar diagonalmente hacia abajo y derecha
                 if (i + 3 < n && j + 3 < n &&
                     base == dna[i + 1].charAt(j + 1) &&
                     base == dna[i + 2].charAt(j + 2) &&
                     base == dna[i + 3].charAt(j + 3)) {
                     return true;
                 }
                 
                 // Verificar diagonalmente hacia abajo e izquierda
                 if (i + 3 < n && j - 3 >= 0 &&
                     base == dna[i + 1].charAt(j - 1) &&
                     base == dna[i + 2].charAt(j - 2) &&
                     base == dna[i + 3].charAt(j - 3)) {
                     return true;
                 }
             }
         }
		return false;
    }
}