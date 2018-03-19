package com.intergalacticcallcenter.attentionbuckect;

import com.intergalacticcallcenter.dto.Call;
import com.intergalacticcallcenter.dto.CallResponse;

public interface AttentionBucketController {
	
	public CallResponse addCall(Call call);

}
