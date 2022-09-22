package TargetClass;

public class BookDaoService {
    BookDao bookDao;
    public void save(){
        bookDao.save();
        System.out.println("Book Service On!");
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
        System.out.println("123");
    }
}
