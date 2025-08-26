package com.empresa.demo.api;

public class Item {
    private Long id;
    private String nombre;
    private boolean hecho;

    public Item() {}
    public Item(Long id, String nombre, boolean hecho) {
        this.id = id; this.nombre = nombre; this.hecho = hecho;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public boolean isHecho() { return hecho; }
    public void setHecho(boolean hecho) { this.hecho = hecho; }
}
