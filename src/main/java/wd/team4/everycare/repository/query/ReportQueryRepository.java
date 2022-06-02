package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.QReport;
import wd.team4.everycare.domain.Report;

import java.util.List;

import static wd.team4.everycare.domain.QReport.*;

@Repository
@RequiredArgsConstructor
public class ReportQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Report> findAllContracts() {
        return queryFactory
                .selectFrom(report)
                .where(report.contract.isNotNull())
                .fetch();
    }

    public List<Report> getAllContractsByStatus(int status) {
        return queryFactory
                .selectFrom(report)
                .where(report.contract.isNotNull(),
                        report.status.eq(status))
                .fetch();
    }

    public List<Report> findAllReviews() {
        return queryFactory
                .selectFrom(report)
                .where(report.board.isNotNull())
                .fetch();
    }

    public List<Report> getAllReviewsByStatus(int status) {
        return queryFactory
                .selectFrom(report)
                .where(report.board.isNotNull(),
                        report.status.eq(status))
                .fetch();
    }

}
