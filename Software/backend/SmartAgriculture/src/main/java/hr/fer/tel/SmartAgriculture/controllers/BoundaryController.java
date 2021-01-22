package hr.fer.tel.SmartAgriculture.controllers;

import hr.fer.tel.SmartAgriculture.entities.Boundary;
import hr.fer.tel.SmartAgriculture.entities.Culture;
import hr.fer.tel.SmartAgriculture.entities.MeasurementType;
import hr.fer.tel.SmartAgriculture.models.BoundaryModel;
import hr.fer.tel.SmartAgriculture.repositories.BoundaryRepository;
import hr.fer.tel.SmartAgriculture.repositories.CultureRepository;
import hr.fer.tel.SmartAgriculture.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/boundaries")
public class BoundaryController {

    private final BoundaryRepository boundaryRepository;
    private final CultureRepository cultureRepository;

    public BoundaryController(BoundaryRepository boundaryRepository, CultureRepository cultureRepository) {
        this.boundaryRepository = boundaryRepository;
        this.cultureRepository = cultureRepository;
    }

    @GetMapping("/{cultureId}")
    public ResponseEntity<?> getBoundaryModel(@PathVariable Long cultureId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Boundary> boundary = this.boundaryRepository.getByUserIdAndCultureCultureId(userPrincipal.getUser().getId(), cultureId);

        if (boundary.isPresent())
            return ResponseEntity.ok(new BoundaryModel(boundary.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<?> addBoundary(@RequestBody BoundaryModel boundaryModel) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Culture> culture = this.cultureRepository.findById(boundaryModel.getCultureId());

        if (culture.isEmpty())
            return ResponseEntity.badRequest().build();

        Optional<Boundary> boundary = this.boundaryRepository.getByUserIdAndCultureCultureId(userPrincipal.getUser().getId(), boundaryModel.getCultureId());

        if (boundary.isPresent()) {
            Boundary existingBoundary = boundary.get();
            Boundary changedBoundary = boundaryModel.toBoundary();

            for (MeasurementType type : MeasurementType.values()) {
                for (boolean b : new boolean[]{true, false}) {
                    Double value = Boundary.getBoundaryValue(type, b).apply(changedBoundary);

                    if (changedBoundary != null)
                        Boundary.getBoundarySetter(type, b).accept(existingBoundary, value);
                }
            }

            this.boundaryRepository.save(existingBoundary);
        } else {
            Boundary newBoundary = boundaryModel.toBoundary();
            newBoundary.setUser(userPrincipal.getUser());
            newBoundary.setCulture(culture.get());

            this.boundaryRepository.save(newBoundary);
        }

        return ResponseEntity.ok().build();
    }

}
