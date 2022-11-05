package com.mintic.seguridadGr2.Controladores;

import com.mintic.seguridadGr2.Modelos.Roles;
import com.mintic.seguridadGr2.Modelos.Usuarios;
import com.mintic.seguridadGr2.Repositorios.RepositorioRoles;
import com.mintic.seguridadGr2.Repositorios.RepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class ControladorUsuarios {
    @Autowired
    private RepositorioUsuarios miRepositorioUsuarios;

    @Autowired
    private RepositorioRoles miRepositorioRoles;

    @GetMapping("")
    public List<Usuarios> index(){
        return this.miRepositorioUsuarios.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Usuarios create(@RequestBody Usuarios infoUsuarios){

        return this.miRepositorioUsuarios.save(infoUsuarios);
    }

    @PutMapping("{id}")
    public Usuarios update(@PathVariable String id, @RequestBody Usuarios infoUsuarios) {
        Usuarios usuario = this.miRepositorioUsuarios.findById(id).orElse(null);
        if (usuario != null) {
            usuario.setSeudonimo(infoUsuarios.getSeudonimo());
            usuario.setCorreo(infoUsuarios.getCorreo());
            usuario.setContrasena(convertirSHA256(infoUsuarios.getContrasena()));
            return this.miRepositorioUsuarios.save(usuario) ;
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id) {
        Usuarios usuario = this.miRepositorioUsuarios .findById(id).orElse(null);
        if (usuario != null) {
            this.miRepositorioUsuarios .delete(usuario);
        }
    }

    @GetMapping("{id}")
    public Usuarios show(@PathVariable String id) {
        Usuarios usuario = this.miRepositorioUsuarios.findById(id).orElse(null);
        return usuario;
    }

    @PutMapping("{id_usuario}/rol/{id_rol}")
    public Usuarios setRol(@PathVariable String id_usuario, @PathVariable String id_rol) {
        Usuarios usuario = this.miRepositorioUsuarios.findById(id_usuario).orElse(null);
        Roles rol = this.miRepositorioRoles.findById(id_rol).orElse(null);

        if (usuario != null && rol != null) {
            usuario.setRoles(rol);
            return this.miRepositorioUsuarios.save(usuario);
        } else {
            return null;
        }

    }


    public String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
