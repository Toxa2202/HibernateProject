package com.saint.anthony;

import com.saint.anthony.models.Product;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//
//        Product product = new Product();
//        Set<ConstraintViolation<Product>> constrs = validator.validate(product);
//
//        for (ConstraintViolation<Product> constr : constrs) {
//            StringBuilder stringBuilder = new StringBuilder("property: ");
//            stringBuilder.append(constr.getPropertyPath());
//            stringBuilder.append(" value: ");
//            stringBuilder.append(constr.getInvalidValue());
//            stringBuilder.append(" message: ");
//            stringBuilder.append(constr.getMessage());
//
//            System.out.println(stringBuilder.toString());
//        }

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Product> products = null;
        try {
            session.beginTransaction();
//            SQLQuery query = session.createSQLQuery("SELECT * FROM product");
//            query.addEntity(Product.class);
//            products = query.list();

//            Query query = session.createQuery(
//                    "from Product as p inner join fetch p.productCategory as pc");
//            products = query.list();

            Criteria criteria = session.createCriteria(Product.class, "product");
            criteria.createCriteria("product.productCategory", "productCategory");
            criteria.add(Restrictions.eq("product.title", "tomatoes"));
//            criteria.setMaxResults(5);
            criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
//            criteria.add(Restrictions.between("price", BigDecimal.valueOf(10), BigDecimal.valueOf(30)));
//            criteria.add(Restrictions.eq("title", "tomatoes"));
            // select * from product where title = "tomatoes"
            products = criteria.list();

            session.getTransaction().commit();

        } catch (Exception ex) {
            session.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }

//        for (Product product : products) {
//            System.out.println(product.toString());
//        }

//        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
//            Product product = (Product) iterator.next();
//            System.out.println(product.toString());
//        }

        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
