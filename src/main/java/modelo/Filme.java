package modelo;

public class Filme {
    private int id_filme;
    private String nome;
    private int quantidade;
    private int id_categoria;
    private int id_locadora;

    //getters e setters
    
    public int getId_filme()
    { return id_filme; 
    }
    public void setId_filme(int id_filme){
        this.id_filme = id_filme; 
    }

    public String getNome(){ 
        return nome; 
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public int getQuantidade(){ 
        return quantidade; 
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade; 
    }

    public int getId_categoria(){
        return id_categoria; 
    }
    public void setId_categoria(int id_categoria){
        this.id_categoria = id_categoria; 
    }

    public int getId_locadora(){
        return id_locadora; 
    }
    public void setId_locadora(int id_locadora){
        this.id_locadora = id_locadora;
    }
}
