package org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.Requests.GetCostRequest;

public class GetCostRequestConverterStrategy implements RequestConverterStrategy
{
    private final String referenceRequestType = RequestTypes.GET_COST_REQUEST;

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        GetCostRequest request = (GetCostRequest) object;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            bytes = objectMapper.writeValueAsBytes(request);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromByteToObject(byte[] bytes) {
        GetCostRequest request = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            request = mapper.readValue(bytes, GetCostRequest.class);
        }
        catch(Exception ex)
        {
            return  null;
        }
        return request;
    }

    @Override
    public String getReferenceRequestType() {
        return this.referenceRequestType;
    }
}
