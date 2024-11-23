/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author noufalmansour
 */
public class Document {
    private int id;
    private String content;

    public Document(int id, String content) {
        this.id = id;
        this.content = content.toLowerCase();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

