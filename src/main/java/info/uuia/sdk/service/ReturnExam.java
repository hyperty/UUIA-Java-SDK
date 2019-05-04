package info.uuia.sdk.service;

import com.alibaba.fastjson.JSONObject;
import info.uuia.sdk.domain.exam.Exam;
import info.uuia.sdk.repository.GetExam;
import info.uuia.sdk.exception.LackNecessaryInfoException;
import info.uuia.sdk.exception.NotImplementedException;
import info.uuia.sdk.util.UuiaLogger;

class ReturnExam {
    Exam getResponse(JSONObject request) {
        try {
            Exam response = new GetExam().getData((String) request.get("uuid"));
            if (check(response)) {
                return response;
            }
        } catch (LackNecessaryInfoException e) {
            e.printStackTrace();
        } catch (NotImplementedException e) {
            e.printStackTrace();
            UuiaLogger.e(getClass().getName(), "没有实现方法");
        }
        return null;
    }

    private boolean check(Exam data) throws LackNecessaryInfoException {
        if (data.getUuid()==null) {
            throw new LackNecessaryInfoException("Need String uuid as a property of " + data.getClass().getName());
        } else if (data.getCourses()==null) {
            throw new LackNecessaryInfoException("Need Array courses as a property of " + data.getClass().getName());
        }
        return true;
    }
}
