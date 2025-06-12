package com.in.sms.repository.custom;

import com.in.sms.dto.StudentSearchDto;
import com.in.sms.model.Student;
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
public class StudentCustomRepositoryImpl implements StudentCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Student> searchStudents(StudentSearchDto dto, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        List<Predicate> predicates = new ArrayList<>();

        if (dto.getStdName() != null && !dto.getStdName().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + dto.getStdName().toLowerCase() + "%"));
        }

        if (dto.getSem() != null && !dto.getSem().isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("semester").get("name")), dto.getSem().toLowerCase()));
        }

        if (dto.getBranch() != null && !dto.getBranch().isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("branch")), dto.getBranch().toLowerCase()));
        }

        if (dto.getCategory() != null && !dto.getCategory().isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("category")), dto.getCategory().toLowerCase()));
        }

        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("address")), "%" + dto.getAddress().toLowerCase() + "%"));
        }

        if (dto.getRollNo() != null && !dto.getRollNo().isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("rollNo")), dto.getRollNo().toLowerCase()));
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            predicates.add(cb.equal(cb.lower(root.get("email")), dto.getEmail().toLowerCase()));
        }

        cq.where(cb.and(predicates.toArray(new Predicate[0])));

        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order order : pageable.getSort()) {
                orders.add(order.isAscending() ? cb.asc(root.get(order.getProperty())) : cb.desc(root.get(order.getProperty())));
            }
            cq.orderBy(orders);
        }

        TypedQuery<Student> query = entityManager.createQuery(cq);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        List<Student> students = query.getResultList();
        return new PageImpl<>(students, pageable, students.size());
    }
}
