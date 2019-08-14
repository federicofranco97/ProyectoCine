package ar.edu.ub.progiii.mvc.service;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Data;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    Data dataManager = new Data();
    MappingTool mappingTool = new MappingTool();

    public ClientDTO GetClientByUID(String UID){
            String response = dataManager.GetClientByUId(UID);
            if(response==null){
                //No se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
            }
            ClientDTO clientDTO = mappingTool.MapDTOClientSQL(response);
            return clientDTO;
    }

}
