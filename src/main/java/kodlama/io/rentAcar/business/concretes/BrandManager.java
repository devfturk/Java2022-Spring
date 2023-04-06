package kodlama.io.rentAcar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.rentAcar.business.abstracts.BrandService;
import kodlama.io.rentAcar.business.requests.CreateBrandRequest;
import kodlama.io.rentAcar.business.requests.UpdateBrandRequest;
import kodlama.io.rentAcar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentAcar.business.responses.GetByIdBrandResponse;
import kodlama.io.rentAcar.business.rules.BrandBusinessRules;
import kodlama.io.rentAcar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentAcar.dataAccess.abstracts.BrandRepository;
import kodlama.io.rentAcar.entities.concretes.Brand;
import lombok.AllArgsConstructor;

@Service //Bu sınıf bir business nesnesidir.
@AllArgsConstructor
public class BrandManager implements BrandService{

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;
	private BrandBusinessRules brandBusinessRules;


	@Override
	public List<GetAllBrandsResponse> getAll() {
		//iş kuralları.
//		List<Brand> brands =brandRepository.findAll();
//		List<GetAllBrandsResponse> brandsResponses = new ArrayList<GetAllBrandsResponse>();
//		for (Brand brand : brands) {
//			GetAllBrandsResponse responseItem = new GetAllBrandsResponse();
//			responseItem.setId(brand.getId());
//			responseItem.setName(brand.getName());
//			brandsResponses.add(responseItem);
//		}
		List<Brand> brands =brandRepository.findAll();
		List<GetAllBrandsResponse> brandsResponses = brands.stream()
				.map(brand ->this.modelMapperService.forResponse()
						.map(brand,GetAllBrandsResponse.class)).collect(Collectors.toList());
		return brandsResponses;
	}


	@Override
	public void add(CreateBrandRequest createBrandRequest) {
		
		//Brand brand = new Brand();
		//brand.setName(createBrandRequest.getName());
		this.brandBusinessRules.checkIfBrandNameExists(createBrandRequest.getName());
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
	}


	@Override
	public GetByIdBrandResponse geyById(int id) {
		Brand brand = this.brandRepository.findById(id).orElseThrow();
		GetByIdBrandResponse response = this.modelMapperService.forResponse().map(brand, GetByIdBrandResponse.class);
		
		return response;
	}


	@Override
	public void update(UpdateBrandRequest updateBrandRequest) {
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		
	}


	@Override
	public void delete(int id) {

		this.brandRepository.deleteById(id);
	}

}