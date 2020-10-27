/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fedts
 */
@Repository
public class ProdutoJpaRepository implements ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Produto> findAll(int offset, int quantidade) {
        TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findAll", Produto.class);
        jpqlQuery.setFirstResult(offset);
        jpqlQuery.setMaxResults(quantidade);
        return jpqlQuery.getResultList();
    }

    @Override
    public List<Produto> findByCategoria(List<Integer> idsCat, int offset, int quantidade) {
        TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findByCategoriasId", Produto.class);
        jpqlQuery.setParameter("idsCat", idsCat);
        jpqlQuery.setFirstResult(offset);
        jpqlQuery.setMaxResults(quantidade);
        return jpqlQuery.getResultList();
    }

    @Override
    public Produto findById(Long id) {
        TypedQuery<Produto> jpqlQuery = em.createNamedQuery("Produto.findById", Produto.class);
        jpqlQuery.setParameter("idProd", id);
        return jpqlQuery.getSingleResult();
    }

    @Transactional
    @Override
    public Produto save(Produto p) {
        if (p.getId() == null) {
            // ADICIONA PRODUTO NOVO
            em.persist(p);
        } else {
            // ATUALIZA PRODUTO EXISTENTE
            p = em.merge(p);
        }
        return p;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        // CONSULTA PARA CARREGAR OBJETO NO ENTITYMANAGER
        // (p ATTACHED NO ENTITYMANAGER)
        Produto p = em.find(Produto.class, id);
        em.remove(p);
    }

}
