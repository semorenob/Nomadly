package com.example.Nomadly.controller;

import com.example.Nomadly.model.Documento;
import com.example.Nomadly.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<Documento> registrarDocumento(@RequestBody Documento documento) {
        Documento nuevoDocumento = documentoService.registrarDocumento(documento);
        return ResponseEntity.ok(nuevoDocumento);
    }

    @GetMapping
    public ResponseEntity<List<Documento>> listarDocumentos() {
        return ResponseEntity.ok(documentoService.obtenerTodos());
    }

    @GetMapping("/contrato/{idContrato}")
    public ResponseEntity<List<Documento>> obtenerPorContrato(@PathVariable Long idContrato) {
        return ResponseEntity.ok(documentoService.obtenerPorContrato(idContrato));
    }
}