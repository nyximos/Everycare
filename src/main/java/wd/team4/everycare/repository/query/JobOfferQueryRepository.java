package wd.team4.everycare.repository.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import wd.team4.everycare.domain.JobOffer;

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
        /*TODO 이거 주소 저장할때 어떤식으로 저장되는지 물어보고 값 두개 이상필요하면 수정*/
        return queryFactory
                .select(jobOffer)
                .from(jobOffer)
                .where(jobOffer.careTarget.address.contains(region))
                .fetch();
    }

}
