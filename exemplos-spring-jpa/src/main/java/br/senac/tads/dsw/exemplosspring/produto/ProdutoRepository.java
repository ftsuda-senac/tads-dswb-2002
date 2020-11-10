/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.dsw.exemplosspring.produto;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fedts
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    // Sintaxe para definir os nomes dos métodos do Spring Data JPA
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    List<Produto> findByNome(String nome);
    
    List<Produto> findByNomeIgnoreCase(String nome);
    
    List<Produto> findByNomeContainingIgnoreCase(String nome);
   
    // USAR JPQL COM Spring Data JPA.
    @Query("SELECT p FROM Produto p WHERE upper(p.nome) LIKE upper('%'||:termoBusca||'%')")
    List<Produto> buscarPorNomeJpql(@Param("termoBusca") String nome);
    
    // USAR SQL NATIVO COM Spring Data JPA.
    @Query(value = "SELECT * FROM PRODUTO WHERE upper(nome) LIKE upper('%'||:termoBusca||'%')", nativeQuery = true)
    List<Produto> buscarPorNomeSql(@Param("termoBusca") String nome);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
    Page<Produto> findByCategorias_IdIn(List<Integer> idsCat, Pageable page);

}
