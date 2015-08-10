package stp.repository.quartz.dao.not_auto;

import java.util.List;
import org.springframework.stereotype.Component;
import stp.repository.quartz.model.ScheduleJob;

@Component
public interface NotAutoScheduleJobMapper {
	int deleteByPrimaryKey(String pkid);

	int insert(ScheduleJob record);

	int insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(String pkid);

	int updateByPrimaryKeySelective(ScheduleJob record);

	int updateByPrimaryKey(ScheduleJob record);

	List<ScheduleJob> getAll();
}