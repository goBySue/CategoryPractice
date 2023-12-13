package javat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component
@Service
public class Catdao {


    //@Autowired

    JdbcTemplate template;

    public Catdao(JdbcTemplate template){
        this.template = template;
    }
//    public void setTemplate(JdbcTemplate template) {
//        this.template = template;
//    }


    public List<Category> display() throws ClassNotFoundException, SQLException {
        // create an array list that will contain the daya recovered

        return template.query("select * from category", (RowMapper) (rs, row) -> {
            Category c = new Category();
            c.setCatcode(rs.getString(1));
            c.setCatdesc(rs.getString(2));
            return c;

        });
    }

    public List <Map<String, Object>> getcat (String cat)
    {
        return template.queryForList("select * from category where catcode =?", cat);
    }
    public int insertData(final Category category)
    {
        return  template.update("insert into category values (?,?)", category.getCatcode(), category.getCatdesc());
    }

    public int deleteData(String cat)
    {
        return template.update("delete * from category where catcode =?", cat);
    }

    public int editData(final Category category, String cat)
    {
        return template.update("update cetegory set catcode = ?, catdesc =?, where catcode=?", category.getCatcode(), category.getCatdesc());
    }
    public void setTemplate(JdbcTemplate template) {
    }



}
