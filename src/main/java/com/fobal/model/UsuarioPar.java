package com.fobal.model;

import java.util.HashSet;
import java.util.Set;

import com.fobal.repository.entity.UsuarioEntity;

public class UsuarioPar {
	
    private final Set<UsuarioEntity> par;

    public UsuarioPar(UsuarioEntity first, UsuarioEntity second) {
        par = new HashSet<>();
        par.add(first);
        par.add(second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPar that = (UsuarioPar) o;
        return par.equals(that.par);
    }

    @Override
    public int hashCode() {
        return par.hashCode();
    }
}





