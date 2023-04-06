package kodlama.io.rentAcar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.rentAcar.business.abstracts.ModelService;
import kodlama.io.rentAcar.business.requests.CreateModelRequest;
import kodlama.io.rentAcar.business.responses.GetAllModelsResponse;
import kodlama.io.rentAcar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentAcar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentAcar.entities.concretes.Model;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService{

	private ModelRepository modelRepository;
	private ModelMapperService modelMapperService;
	
	@Override
	public List<GetAllModelsResponse> getAll() {
		List<Model> models = modelRepository.findAll();
		List<GetAllModelsResponse> modelsResponses = models.stream()
				.map(model ->this.modelMapperService.forResponse()
						.map(model,GetAllModelsResponse.class)).toList();
		return modelsResponses;
	}

	@Override
	public void add(CreateModelRequest createModelRequest) {
		// TODO Auto-generated method stub
		Model model = this.modelMapperService.forRequest().map(createModelRequest, Model.class);
		this.modelRepository.save(model);
	}

}
