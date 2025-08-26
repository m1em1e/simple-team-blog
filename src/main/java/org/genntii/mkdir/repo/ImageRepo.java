package org.genntii.mkdir.repo;

import org.genntii.mkdir.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author mkdir
 * @since 2025/08/22 15:22
 */
public interface ImageRepo extends JpaRepository<Image, Long> {
}
