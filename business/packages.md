# Package com.capraro.business

This package contains the definition of the [UseCase][com.capraro.business.UseCase] interface.
Its subpackages contains the gateways, interactors, model and usecases.


# Package com.capraro.business.order.usecase

This package contains the interface of the usecases.

# Package com.capraro.business.order.model

This package contains model: [Order][com.capraro.business.order.model.Order] and its Items.

# Package com.capraro.business.order.interactor

This package contains the implementation of the usecases. Theses implementations collaborates with the gateways.

# Package com.capraro.business.order.gateway

This package contains the gateways (aka the interface used to access the infrastructure modules). 