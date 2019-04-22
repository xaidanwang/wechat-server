package com.github.aidan.gar.demo.eureka.bean;

import lombok.Data;

/**
 * @Description TODO
 * @Author zhangweizheng
 * @TIME 2019-03-01 09:17
 **/
@Data
public class LeaseInfo {
        private Integer renewalIntervalInSecs;
        private Integer durationInSecs;
        private Long    registrationTimestamp;
        private Long    lastRenewalTimestamp;
        private Long    evictionTimestamp;
        private Long    serviceUpTimestamp;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                if (!super.equals(o)) return false;

                LeaseInfo leaseInfo = (LeaseInfo) o;

                if (renewalIntervalInSecs != null ? !renewalIntervalInSecs.equals(leaseInfo.renewalIntervalInSecs) : leaseInfo.renewalIntervalInSecs != null)
                        return false;
                if (durationInSecs != null ? !durationInSecs.equals(leaseInfo.durationInSecs) : leaseInfo.durationInSecs != null)
                        return false;
                if (registrationTimestamp != null ? !registrationTimestamp.equals(leaseInfo.registrationTimestamp) : leaseInfo.registrationTimestamp != null)
                        return false;
                if (lastRenewalTimestamp != null ? !lastRenewalTimestamp.equals(leaseInfo.lastRenewalTimestamp) : leaseInfo.lastRenewalTimestamp != null)
                        return false;
                if (evictionTimestamp != null ? !evictionTimestamp.equals(leaseInfo.evictionTimestamp) : leaseInfo.evictionTimestamp != null)
                        return false;
                return serviceUpTimestamp != null ? serviceUpTimestamp.equals(leaseInfo.serviceUpTimestamp) : leaseInfo.serviceUpTimestamp == null;
        }

        @Override
        public int hashCode() {
                int result = super.hashCode();
                result = 31 * result + (renewalIntervalInSecs != null ? renewalIntervalInSecs.hashCode() : 0);
                result = 31 * result + (durationInSecs != null ? durationInSecs.hashCode() : 0);
                result = 31 * result + (registrationTimestamp != null ? registrationTimestamp.hashCode() : 0);
                result = 31 * result + (lastRenewalTimestamp != null ? lastRenewalTimestamp.hashCode() : 0);
                result = 31 * result + (evictionTimestamp != null ? evictionTimestamp.hashCode() : 0);
                result = 31 * result + (serviceUpTimestamp != null ? serviceUpTimestamp.hashCode() : 0);
                return result;
        }
}
