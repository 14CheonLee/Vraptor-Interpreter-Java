package com.interpreter.boundary;

import com.interpreter.dto.node.NodeData;
import com.interpreter.system.BinaryAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/node")
public class NodeResource extends ResourceObject {

    private static final Logger logger = LoggerFactory.getLogger(NodeResource.class);
    private BinaryAccess binaryAccess = BinaryAccess.getInstance();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getDefaultStatus() {
        JsonObject statusJsonObject = Json.createObjectBuilder()
                .add("status", "ok")
                .build();

        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/set_power_status")
    public Response setNodePowerStatus(@FormParam("node_num") int nodeNumber, @FormParam("power_status") boolean powerStatus) {
        JsonObject statusJsonObject;

        try {
//            NodeData nodeData = new NodeData.NodeDataBuilder()
//                    .setNodeNumber(nodeNumber)
//                    .setPowerStatus(powerStatus)
//                    .build();

            binaryAccess.setPowerStatus(nodeNumber, powerStatus);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "ok")
                    .build();
        } catch (Exception e) {
            logger.error("Failed to set 'power_status' data to system : " + e);

            statusJsonObject = Json.createObjectBuilder()
                    .add("status", "fail")
                    .build();
        }

        return Response.ok(statusJsonObject.toString()).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_all_power_status")
    public Response getAllNodePowerStatus() {
        return null;
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/get_power_status")
    public Response getNodePowerStatus(@FormParam("node_num") int nodeNumber) {
        return null;
    }
}
