package com.dz.generator.dto;


public class GeneratorDto {

    private String outputDir;
    private String author;
    private String tableNames;
    private String packageDir;
    private String tpls;
    private String name;
    private String comments;
    private String jsonStr;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTableNames() {
        return tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getPackageDir() {
        return packageDir;
    }

    public void setPackageDir(String packageDir) {
        this.packageDir = packageDir;
    }

    public String getTpls() {
        return tpls;
    }

    public void setTpls(String tpls) {
        this.tpls = tpls;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getJsonStr() {
        return jsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.jsonStr = jsonStr;
    }

    @Override
    public String toString() {
        return "GeneratorDto{" +
                "outputDir='" + outputDir + '\'' +
                ", author='" + author + '\'' +
                ", tableNames='" + tableNames + '\'' +
                ", packageDir='" + packageDir + '\'' +
                ", tpls='" + tpls + '\'' +
                ", name='" + name + '\'' +
                ", comments='" + comments + '\'' +
                ", jsonStr='" + jsonStr + '\'' +
                '}';
    }
}
