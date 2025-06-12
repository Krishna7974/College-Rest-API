package com.in.sms.repository.custom;

import com.in.sms.dto.TeacherSearchDto;
import com.in.sms.model.Teacher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherCustomRepositoryImpl implements TeacherCustomRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Teacher> searchTeacher(TeacherSearchDto dto, Pageable pageable) {

        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> cq=cb.createQuery(Teacher.class);
        Root<Teacher> root=cq.from(Teacher.class);
        List<Predicate> pred=new ArrayList<>();

        if(dto.getName()!=null && !dto.getName().isBlank()){
            pred.add(cb.like(cb.lower(root.get("name")),"%"+dto.getName()+"%"));
        }

        if(dto.getEmail()!=null && !dto.getEmail().isBlank()){
            pred.add(cb.equal(cb.lower(root.get("email")),dto.getEmail()));
        }

        if(dto.getSubject()!=null && !dto.getSubject().isBlank()){
            pred.add(cb.like(cb.lower(root.get("subject").get("name")),"%"+dto.getSubject()+"%"));
        }

        cq.where(cb.and(pred.toArray(new Predicate[0])));
        cq.distinct(true);

        if (pageable.getSort().isSorted()){
            List<Order> orders=new ArrayList<>();
            for(Sort.Order o:pageable.getSort()){
                orders.add(o.isAscending()?cb.asc(root.get(o.getProperty())):cb.desc(root.get(o.getProperty())));
            }
            cq.orderBy(orders);
        }

        TypedQuery<Teacher> query=entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Teacher> teachers=query.getResultList();
        return new PageImpl<>(teachers,pageable,teachers.size());
    }
}
