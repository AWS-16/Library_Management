public class Book {

    private   int id ;
    private String title;
    private String author ;
    private String publishedYear;
    private String status;

    private static int autoNum=0;

    public Book(){

      id= ++autoNum;
      status="Available";

     }

    public int getId() { return id; }
    public void setId(int id){this.id=id; }

    public String getTitle(){ return title; }
    public void setTitle(String title){ this.title= title; }

    public String getAuthor(){return author;}
    public void setAuthor(String author ){ this.author = author; }

    public String getStatus(){return status; }
    public void setStatus(String status){ this.status= status; }

    public String getPublishedYear(){return publishedYear; }
    public void setPublishedYear(String publishedYear){this.publishedYear = publishedYear;}

}
