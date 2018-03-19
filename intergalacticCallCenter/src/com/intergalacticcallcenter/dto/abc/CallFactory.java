package com.intergalacticcallcenter.dto.abc;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.Employee;

public interface CallFactory {

	Call getCallWithId(Call call);

	Call getCallWithEmployee(Call call, Employee employee);

	Call getCallFinnished(Call call);

	Call getCallStarted(Call call);

}
