package com.rezourcesched.rez.rezource;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.rezourcesched.rez.entity.Person;
import com.rezourcesched.rez.entity.Rezource;
import com.rezourcesched.rez.entity.RezourceType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@RequestMapping("/rezource")
@OpenAPIDefinition(info = @Info(title = "Rezource Scheduler"), servers = {@Server(url = "http://localhost:8080", description = "Local server")})
public interface iRezourceController {
  
  @Operation(
      summary = "Returns a list of Rezources",
      description = "Returns a list of Rezources given a rezourceId, state, postalCode, rezourcerId or rezourceType",
      responses = {
          @ApiResponse(
              responseCode = "200", 
              description = "A list of Rezources is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Rezource.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Rezources were found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "rezourceId", 
              allowEmptyValue = false, 
              required = false, 
              description = "The unique key id given to the resource generated by the database"),
          @Parameter(
              name = "state", 
              allowEmptyValue = false, 
              required = false, 
              description = "Look at all of the Rezources in the state"),
          @Parameter(
              name = "postalCode", 
              allowEmptyValue = false, 
              required = false, 
              description = "Search for all the Rezources in a zip code"),
          @Parameter(
              name = "rezourceType", 
              allowEmptyValue = false, 
              required = false, 
              description = "The type of Rezource: Service, Place or Thing"),
          @Parameter(
              name = "rezourcerId", 
              allowEmptyValue = false, 
              required = false, 
              description = "The person who owns the Rezource")
      }
      
  )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Rezource> fetchRezource(
      @RequestParam(required = false) Long rezourceId, 
      @RequestParam(required = false) String state,
      @RequestParam(required = false) String postalCode,
      @RequestParam(required = false) RezourceType rezourceType,
      @RequestParam(required = false) Long rezourcerId);
  
  @Operation(
      summary = "Creates a Rezource",
      description = "Creates a Rezource (service, place or thing) to rent for a fee)",
      responses = {
          @ApiResponse(
              responseCode = "201", 
              description = "The Rezource created is returned", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Rezource.class))),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "rezource", 
              allowEmptyValue = false, 
              required = true, 
              description = "JSON body of a Rezource")
      }  
  )
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Rezource createRezource(
      @RequestBody(required = true) Rezource rezource);
  
  @Operation(
      summary = "Update a Rezource",
      description = "Update attributes of a Rezource by rezourceId",
      responses = {
          @ApiResponse(
              responseCode = "202", 
              description = "Rezource Updated", 
              content = @Content(mediaType = "application/json", 
              schema = @Schema(implementation = Rezource.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(
              name = "rezource", 
              allowEmptyValue = false, 
              required = true, 
              description = "JSON body of a rezource")
      }
      
  )
   
  @PutMapping
  @ResponseStatus(code = HttpStatus.ACCEPTED)
  Rezource reviseRezource(
      @RequestBody(required = true) Rezource rezource);
}
