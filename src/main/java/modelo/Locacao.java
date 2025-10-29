package modelo;

import java.time.LocalDate;

public class Locacao {
    private int id_locacao;
    private LocalDate data_locacao;
    private LocalDate data_devolucao;
    private int id_cliente;
    private int id_locadora;
    private int id_filme;
    
    //getters e setters

    public int getId_locacao(){
        return id_locacao; 
    }
    public void setId_locacao(int id_locacao){
        this.id_locacao = id_locacao; 
    }

    public LocalDate getData_locacao(){
        return data_locacao; 
    }
    public void setData_locacao(LocalDate data_locacao){
        this.data_locacao = data_locacao; 
    }

    public LocalDate getData_devolucao(){
        return data_devolucao; 
    }
    public void setData_devolucao(LocalDate data_devolucao){ 
        this.data_devolucao = data_devolucao; 
    }

    public int getId_cliente(){ 
        return id_cliente; 
    }
    public void setId_cliente(int id_cliente){
        this.id_cliente = id_cliente; 
    }

    public int getId_locadora(){
        return id_locadora; 
    }
    public void setId_locadora(int id_locadora){
        this.id_locadora = id_locadora; 
    }

    public int getId_filme(){ return id_filme; 
    }
    public void setId_filme(int id_filme){ 
        this.id_filme = id_filme; 
    }
}
