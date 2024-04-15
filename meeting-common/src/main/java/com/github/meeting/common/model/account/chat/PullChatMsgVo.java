package com.github.meeting.common.model.account.chat;

import com.github.meeting.common.model.account.page.PageVo;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.Period;

/**
 * @author pengpeng
 * @description
 * @date 2023/7/2
 */
@Data
public class PullChatMsgVo implements Serializable {

    @NotNull(message = "sessionId could not be null, type legal value!")
    private Long sessionId ;

//    @NotNull(message = "timeRange could not be null")

    private PageVo pageVo;


//
//    public PageRequest getPageRequest(){
//        if (pageVo != null  &&  getPageVo().pageSize() > 0 && getPageVo().pageNumber() >= 0){
//            PageRequest of = PageRequest.of(getPageVo().pageNumber(), getPageVo().pageSize());
//            return of;
//        }
//        return null;
//    }





     @Data
    public static class TimeRange{
//        /**
//         * Obtains a {@code Period} from a text string such as {@code PnYnMnD}.
//         * <p>
//         * This will parse the string produced by {@code toString()} which is
//         * based on the ISO-8601 period formats {@code PnYnMnD} and {@code PnW}.
//         * <p>
//         * The string starts with an optional sign, denoted by the ASCII negative
//         * or positive symbol. If negative, the whole period is negated.
//         * The ASCII letter "P" is next in upper or lower case.
//         * There are then four sections, each consisting of a number and a suffix.
//         * At least one of the four sections must be present.
//         * The sections have suffixes in ASCII of "Y", "M", "W" and "D" for
//         * years, months, weeks and days, accepted in upper or lower case.
//         * The suffixes must occur in order.
//         * The number part of each section must consist of ASCII digits.
//         * The number may be prefixed by the ASCII negative or positive symbol.
//         * The number must parse to an {@code int}.
//         * <p>
//         * The leading plus/minus sign, and negative values for other units are
//         * not part of the ISO-8601 standard. In addition, ISO-8601 does not
//         * permit mixing between the {@code PnYnMnD} and {@code PnW} formats.
//         * Any week-based input is multiplied by 7 and treated as a number of days.
//         * <p>
//         * For example, the following are valid inputs:
//         * <pre>
//         *   "P2Y"             -- Period.ofYears(2)
//         *   "P3M"             -- Period.ofMonths(3)
//         *   "P4W"             -- Period.ofWeeks(4)
//         *   "P5D"             -- Period.ofDays(5)
//         *   "P1Y2M3D"         -- Period.of(1, 2, 3)
//         *   "P1Y2M3W4D"       -- Period.of(1, 2, 25)
//         *   "P-1Y2M"          -- Period.of(-1, 2, 0)
//         *   "-P1Y2M"          -- Period.of(-1, -2, 0)
//         * </pre>
//         *
//         * @param text  the text to parse, not null
//         * @return the parsed period, not null
//         * @throws DateTimeParseException if the text cannot be parsed to a period
//         */
//        private String period;

         @NotNull
        private String startTime; //YYYY/MM/dd HH:mm:ss

         @NotNull
        private String endTime;



        public static Period defaultPeriod(){
            return Period.of(0, 1, 0);
        }
    }








}
