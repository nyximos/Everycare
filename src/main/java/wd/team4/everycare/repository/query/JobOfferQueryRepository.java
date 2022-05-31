package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.JobOffer;

import java.time.LocalDate;
import java.util.List;

import static wd.team4.everycare.domain.QJobOffer.jobOffer;

@Repository
@RequiredArgsConstructor
public class JobOfferQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<JobOffer> findAllByPay(int min, int max){
        return queryFactory
                .select(jobOffer)
                .from(jobOffer)
                .where(jobOffer.pay.between(min,max))
                .fetch();
    }

    public List<JobOffer> findAllByRegion(String region){
        return queryFactory
                .select(jobOffer)
                .from(jobOffer)
                .where(jobOffer.careTarget.address.contains(region))
                .fetch();
    }

    public List<JobOffer> findAllByDate(LocalDate date){
        return queryFactory
                .select(jobOffer)
                .from(jobOffer)
                .where(jobOffer.startDate.after(date).and(jobOffer.endDate.before(date)))
                .fetch();
    }

}
