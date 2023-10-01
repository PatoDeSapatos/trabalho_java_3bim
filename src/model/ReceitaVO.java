package model;

/**
 * @author Felipe Fernandes Alves e Julia Moreira de Paula
 */
public class ReceitaVO {
    private int codigo;
    private String nome;
    private String ingredientes;
    private String dataRegistro;

    public ReceitaVO(int codigo, String nome, String ingredientes, String dataRegistro) {
        this.codigo = codigo;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.dataRegistro = dataRegistro;
    }
    
    //#region getters and setters
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIngredientes() {
        return ingredientes;
    }
    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }
    public String getData() {
        return dataRegistro;
    }
    public void setData(String dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    //#endregion

    @Override
    public String toString() {
        return "ReceitaVO [codigo=" + codigo + ", nome=" + nome + ", ingredientes=" + ingredientes + ", dataRegistro="
                + dataRegistro + "]";
    }
}