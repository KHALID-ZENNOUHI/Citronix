package org.citronix.citronix.service.impl;

import org.citronix.citronix.domain.Farm;
import org.citronix.citronix.domain.Field;
import org.citronix.citronix.domain.Tree;
import org.citronix.citronix.repository.FieldRepository;
import org.citronix.citronix.service.FarmService;
import org.citronix.citronix.web.errors.FieldNotFoundException;
import org.citronix.citronix.web.errors.IdMustBeNullException;
import org.citronix.citronix.web.errors.TreesInFieldMustBeEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmService farmService;

    @InjectMocks
    private FieldServiceImpl fieldService;

    @BeforeEach
    void setUp() {
        // Initialize mocks created above
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldSaveFieldSuccessfully() {

        Field field = new Field();
        field.setArea(1.0);
        Farm farm = new Farm();
        farm.setId(1L);
        farm.setArea(10.0);
        field.setFarm(farm);

        when(farmService.findById(1L)).thenReturn(farm);
        when(fieldRepository.save(any(Field.class))).thenReturn(field);


        Field savedField = fieldService.save(field);


        assertNotNull(savedField);
        verify(farmService).findById(1L);
        verify(fieldRepository).save(field);
    }

    @Test
    void save_ShouldThrowExceptionWhenIdIsNotNull() {

        Field field = new Field();
        field.setId(1L);


        assertThrows(IdMustBeNullException.class, () -> fieldService.save(field));
        verifyNoInteractions(fieldRepository, farmService);
    }

    @Test
    void save_ShouldThrowExceptionWhenTreesAreNotEmpty() {

        Field field = new Field();
        field.setTrees(Collections.singletonList(new Tree()));


        assertThrows(TreesInFieldMustBeEmptyException.class, () -> fieldService.save(field));
        verifyNoInteractions(fieldRepository, farmService);
    }

    @Test
    void findById_ShouldReturnFieldWhenFound() {

        Field field = new Field();
        field.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));


        Field foundField = fieldService.findById(1L);


        assertNotNull(foundField);
        assertEquals(1L, foundField.getId());
        verify(fieldRepository).findById(1L);
    }

    @Test
    void findById_ShouldThrowExceptionWhenNotFound() {

        when(fieldRepository.findById(1L)).thenReturn(Optional.empty());


        assertThrows(FieldNotFoundException.class, () -> fieldService.findById(1L));
    }

    @Test
    void delete_ShouldDeleteFieldSuccessfully() {

        Field field = new Field();
        field.setId(1L);

        when(fieldRepository.findById(1L)).thenReturn(Optional.of(field));


        fieldService.delete(1L);


        verify(fieldRepository).deleteById(1L);
    }

    @Test
    void deleteAllByFarmId_ShouldCallRepositoryMethod() {

        fieldService.deleteAllByFarmId(1L);


        verify(fieldRepository).deleteAllByFarmId(1L);
    }

    @Test
    void checkFieldAreaLessThanFiftyPercentOfFarmArea_ShouldThrowExceptionWhenExceedsLimit() {

        Field field = new Field();
        field.setArea(6.0);
        Farm farm = new Farm();
        farm.setArea(10.0);
        field.setFarm(farm);


        assertThrows(IllegalArgumentException.class, () -> fieldService.checkFieldAreaLessThanFiftyPercentOfFarmArea(field));
    }

    @Test
    void checkFieldAreaLessThanFiftyPercentOfFarmArea_ShouldPassWhenWithinLimit() {

        Field field = new Field();
        field.setArea(4.0);
        Farm farm = new Farm();
        farm.setArea(10.0);
        field.setFarm(farm);


        fieldService.checkFieldAreaLessThanFiftyPercentOfFarmArea(field);


    }
}
